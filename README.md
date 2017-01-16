Challenge written on Java. To run it, please, make sure that: 1) java --version >= 7 2) You have maven v.3+ installed

You can find the instructions how to install java and maven below. To run the project, simply start run.sh or run_tests.sh scripts. If there is no Java or Maven installed - you'll see an error in console.

Only 3 required features implemented, using Graph data structure and Breadth-First search algorithm.

To install java:

Make a directory where you want to install Java. For global access (for all users) install it preferably in the directory /opt/java. # mkdir /opt/java && cd /opt/java

download Java (JDK) 8u45 source tarball files for your system architecture by going to official Java download page. http://www.oracle.com/technetwork/java/javase/downloads/index.html

Once file has been downloaded, put it to the folder created earlier and you may extract the tarball using tar command as shown below.

# tar -zxvf jdk-8u45-linux-i586.tar.gz [For 32-bit Systems] # tar -zxvf jdk-8u45-linux-x64.tar.gz [For 64-bit Systems]

Next, move to the extracted directory and use command update-alternatives to tell system where java and its executables are installed.

# cd jdk1.8.0_45/ # update-alternatives --install /usr/bin/java java /opt/java/jdk1.8.0_45/bin/java 100
# update-alternatives --config java

Tell system to update javac alternatives as:

# update-alternatives --install /usr/bin/javac javac /opt/java/jdk1.8.0_45/bin/javac 100 # update-alternatives --config javac

Similarly, update jar alternatives as:

# update-alternatives --install /usr/bin/jar jar /opt/java/jdk1.8.0_45/bin/jar 100 # update-alternatives --config jar

Setting up Java Environment Variables.

# export JAVA_HOME=/opt/java/jdk1.8.0_45/
# export JRE_HOME=/opt/java/jdk1.8.0._45/jre
# export PATH=$PATH:/opt/java/jdk1.8.0_45/bin:/opt/java/jdk1.8.0_45/jre/bin

To install Java for Mac, use homebrew

brew update brew cask install java

Maven installation: http://maven.apache.org/install.html

For Mac users: brew install maven
