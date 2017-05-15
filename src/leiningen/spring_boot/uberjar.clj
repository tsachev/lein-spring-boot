(ns leiningen.spring-boot.uberjar
  (:require [clojurewerkz.propertied.properties :as p]
            [leiningen.core.main :as main])
  (:import (java.util Properties)
           (java.io InputStream)))

(defn- properties-read [^InputStream ins]
  (let [p (doto (Properties.) (.load ins))]
    (p/properties->map p)))

(defn- properties-write [out p]
  (p/store-to p out))

(defn- properties-merge [new prev]
  (merge-with #(str %1 "," %2) new prev))

(defn uberjar
  "Use this task as :prep-task in order to be able to use leiningen.spring-boot.uberjar/properties-merger"
  [project & args]
  (main/debug "executing uberjar task"))

(def properties-merger
  "Project `:uberjar-merge-with` merger for properties files (e.g. META-INF/spring.factories)"
  [properties-read properties-merge properties-write])
