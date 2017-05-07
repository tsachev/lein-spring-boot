(ns leiningen.spring-boot.tools
  (:require [clojure.java.io :as io])
  (:import (org.springframework.boot.loader.tools Repackager
                                                  Repackager$MainClassTimeoutWarningListener
                                                  LibraryScope
                                                  Library
                                                  Libraries
                                                  DefaultLaunchScript)))

(defn repackager [source & [listener?]]
  (let [result (Repackager. (io/file source))]
    (when listener?
      (.addMainClassTimeoutWarningListener
        result
        (reify Repackager$MainClassTimeoutWarningListener
          (handleTimeoutWarning [_ duration main-method]
            (listener? duration main-method)))))
    result))

(defn library [dep]
  (Library. dep LibraryScope/COMPILE))

(defn libraries [deps]
  (reify Libraries
    (doWithLibraries [_ callback]
      (doseq [l (map library deps)]
        (.library callback l)))))

(defn launch-script [props]
  (DefaultLaunchScript. nil props))

(defn repackage [repackager destination libraries launch-script]
  (.repackage repackager (io/file destination) libraries launch-script))
