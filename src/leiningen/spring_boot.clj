(ns leiningen.spring-boot
  (:require [leiningen.help :as help]
            [leiningen.spring-boot.repackage :refer [repackage]]
            [leiningen.spring-boot.build-info :refer [build-info]]
            [leiningen.spring-boot.uberjar :refer [uberjar]]
            [leiningen.core.main :as main]))

(defn spring-boot
  "Provide Spring Boot tools."
  {:help-arglists '([repackage build-info])
   :subtasks      [#'repackage #'build-info]}
  ([project subtask & args]
   (case subtask
     "repackage" (repackage project args)
     "build-info" (build-info project args)
     "uberjar" (uberjar project args)
     (main/warn "Please specify spring-boot goal." (help/subtask-help-for *ns* #'spring-boot)))))
