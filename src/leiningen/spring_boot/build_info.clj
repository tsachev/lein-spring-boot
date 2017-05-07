(ns leiningen.spring-boot.build-info
  (:require [clojure.java.io :as io])
  (:import (org.springframework.boot.loader.tools BuildPropertiesWriter
                                                  BuildPropertiesWriter$ProjectDetails)))

(defn build-info
  "Generate a build-info.properties file for the current project"
  [project & args]
  (-> (BuildPropertiesWriter.
        (io/file (:compile-path project) "META-INF/build-info.properties"))
      (.writeBuildProperties
        (BuildPropertiesWriter$ProjectDetails. (:group project) (:name project) (:version project) (:name project) {}))))
