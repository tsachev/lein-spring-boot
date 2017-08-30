(defproject lein-spring-boot "0.1.0-SNAPSHOT"
  :description "Leiningen plugin that provides spring boot tools functionality as tasks"
  :url "http://github.com/tsachev/lein-spring-boot"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.springframework.boot/spring-boot-loader-tools "1.5.6.RELEASE"]
                 [clojurewerkz/propertied "1.2.0"]]
  :min-lein-version "2.7.0"
  :eval-in-leiningen true)
