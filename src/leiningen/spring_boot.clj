(ns leiningen.spring-boot
  (:require [leiningen.help :as help]
            [leiningen.spring-boot.repackage :refer [repackage]]
            [leiningen.spring-boot.build-info :refer [build-info]]
            [leiningen.core.main :as main]
            [robert.hooke :as hooke]))

(defn spring-boot
  "Provide Spring Boot tools."
  {:help-arglists '([repackage build-info])
   :subtasks      [#'repackage #'build-info]}
  ([project subtask & args]
   (case subtask
     "repackage" (repackage project args)
     "build-info" (build-info project args)
     (main/warn "Please specify spring-boot goal." (help/subtask-help-for *ns* #'spring-boot)))))
