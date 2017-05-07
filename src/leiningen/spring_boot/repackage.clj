(ns leiningen.spring-boot.repackage
  (:require [leiningen.spring-boot.tools :as spring-boot]
            [leiningen.core.project :as project]
            [leiningen.core.classpath :as classpath]
            [leiningen.core.main :as main]
            [leiningen.jar :as jar]))

(defn- libraries [project]
  (let [whitelisted (select-keys project project/whitelist-keys)
        project (-> (project/unmerge-profiles project [:default])
                    (merge whitelisted))
        deps (->> (classpath/resolve-managed-dependencies
                    :dependencies
                    :managed-dependencies
                    project))]
    (spring-boot/libraries deps)))

(defn- listener [duration main-method]
  (main/warn "Searching for the main-class is taking some time, consider using main class configuration"))

(defn- init-info [project]
  {"initInfoProvides"         (:name project)
   "initInfoShortDescription" (:name project)
   "initInfoDescription"      (:description project)})


(defn repackage
  "Repackages existing jar and war archives into spring boot standalone jars"
  [project & args]
  (let [source (jar/get-jar-filename project)
        destination (jar/get-jar-filename project :standalone)
        repackager (spring-boot/repackager source listener)
        libraries (libraries project)
        launch-script (spring-boot/launch-script (init-info project))]
    (spring-boot/repackage repackager destination libraries, launch-script)
    (main/info "Created" destination)
    destination))