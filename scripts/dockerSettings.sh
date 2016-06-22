#!/usr/bin/env bash


#####################################################
#
#       This is a reference guide for docker newcomer (Qiang)
#
#####################################################
# Dockerfile best practices: https://docs.docker.com/articles/dockerfile_best-practices/
#        expose(8090)
#        expose(9999)    // for JMX
#        env("MESOS_VERSION", mesosVersion)
#        runRaw("""echo "deb http://repos.mesosphere.io/ubuntu/ trusty main" > /etc/apt/sources.list.d/mesosphere.list && \
#                  apt-key adv --keyserver keyserver.ubuntu.com --recv E56151BF && \
#                  apt-get -y update && \
#                  apt-get -y install mesos=${MESOS_VERSION} && \
#                  apt-get clean
#               """)
#        copy(artifact, artifactTargetPath)
#        copy(baseDirectory(_ / "bin" / "server_start.sh").value, file("app/server_start.sh"))
#        copy(baseDirectory(_ / "bin" / "server_stop.sh").value, file("app/server_stop.sh"))
#        copy(baseDirectory(_ / "bin" / "setenv.sh").value, file("app/setenv.sh"))
#        copy(baseDirectory(_ / "config" / "log4j-stdout.properties").value, file("app/log4j-server.properties"))
#        copy(baseDirectory(_ / "config" / "docker.conf").value, file("app/docker.conf"))
#        copy(baseDirectory(_ / "config" / "docker.sh").value, file("app/settings.sh"))
#        // Including envs in Dockerfile makes it easy to override from docker command
#        env("JOBSERVER_MEMORY", "1G")
#        env("SPARK_HOME", "/spark")
#        env("SPARK_BUILD", s"spark-$sparkVersion-bin-hadoop2.4")
#        // Use a volume to persist database between container invocations
#        run("mkdir", "-p", "/database")
#        runRaw("""wget http://d3kbcqa49mib13.cloudfront.net/$SPARK_BUILD.tgz && \
#                  tar -xvf $SPARK_BUILD.tgz && \
#                  mv $SPARK_BUILD /spark && \
#                  rm $SPARK_BUILD.tgz
#               """)
#        volume("/database")
#        entryPoint("app/server_start.sh")