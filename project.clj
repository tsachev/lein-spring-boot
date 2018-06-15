(defproject lein-spring-boot "0.1.0-SNAPSHOT"
  :description "Leiningen plugin that provides spring boot tools functionality as tasks"
  :url "http://github.com/tsachev/lein-spring-boot"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.springframework.boot/spring-boot-loader-tools "2.0.3.RELEASE"]
                 [clojurewerkz/propertied "1.3.0"]]
  :min-lein-version "2.8.1"
  :eval-in-leiningen true)
