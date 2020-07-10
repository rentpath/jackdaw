(defproject com.rentpath/jackdaw "0.8.0"
  :description "A Clojure library for the Apache Kafka distributed streaming platform."
  :scm {:name "git" :url "https://github.com/rentpath/jackdaw"}
  :url "https://github.com/rentpath/jackdaw/"
  :repositories [["confluent" {:url "https://packages.confluent.io/maven/"}]]
  :license      {:name "BSD 3-clause"
                 :url  "http://opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[aleph "0.4.6"]
                 [clj-time "0.15.1"]
                 [danlentz/clj-uuid "0.1.9"
                  :exclusions [primitive-math]]
                 ;; Confluent does paired releases with Kafka, this should tie
                 ;; off with the kafka version.
                 ;; See https://docs.confluent.io/current/release-notes.html

                 [io.confluent/kafka-schema-registry-client "5.5.0"]
                 [io.confluent/kafka-avro-serializer "5.5.0"]
                 [org.apache.kafka/kafka-clients "2.5.0"]
                 [org.apache.kafka/kafka-streams "2.5.0"]
                 [org.apache.kafka/kafka-streams-test-utils "2.5.0"]
                 [org.clojure/clojure "1.10.1" :scope "provided"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/data.fressian "0.2.1"]
                 [org.clojure/tools.logging "0.4.1"]
                 [org.clojure/core.cache "0.7.2"]]
  :aliases {"kaocha" ["run" "-m" "kaocha.runner"]}
  :aot [jackdaw.serdes.edn2 jackdaw.serdes.fressian jackdaw.serdes.fn-impl]
  :deploy-repositories [["releases" {:url           "https://clojars.org/repo/"
                                     :username      [:gpg :env/CLOJARS_USERNAME]
                                     :password      [:gpg :env/CLOJARS_PASSWORD]
                                     :sign-releases false}]]
  :profiles {;; Provide an alternative to :leiningen/default, used to include :shared
             :default [:base :system :user :provided :dev]

             ;; The dev profile - non-deployment configuration
             :dev     {:source-paths   ["dev"]
                       :resource-paths ["test/resources"]
                       :injections     [(require 'io.aviso.logging.setup)]
                       :dependencies   [[io.aviso/logging "0.3.2"]
                                        [org.apache.kafka/kafka-streams-test-utils "2.5.0"]
                                        [org.apache.kafka/kafka-clients "2.5.0" :classifier "test"]
                                        [org.clojure/test.check "0.9.0"]
                                        [org.apache.kafka/kafka_2.13 "2.5.0"]
                                        [lambdaisland/kaocha "0.0-529"]
                                        [lambdaisland/kaocha-cloverage "0.0-32"]
                                        [lambdaisland/kaocha-junit-xml "0.0-70"]]}

             ;; This is not in fact what lein defines repl to be
             :repl    [:default :dev]})
