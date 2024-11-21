#! /bin/bash
# 记录脚本开始执行时间
echo '========= githubHosts任务开始执行时间 ======' `date` >> ./runLog
# 执行自定义的脚本，我这里是一个java代码实现
APP_PATH=~/work/Code/VCS
cd $APP_PATH/github-hosts/

FILE=$APP_PATH/java.profile
if [ -f "$FILE" ]; then
    echo "$FILE exist"
    source $FILE
fi

FILE=target/github-hosts-1.0-SNAPSHOT.jar
if [ -f "$FILE" ]; then
    echo "$FILE exist"
else
    mvn clean package -DskipTests
fi
# 开始获取Github的hosts信息
$JAVA_HOME/bin/java -jar target/github-hosts-1.0-SNAPSHOT.jar
# 将获取到的信息注入本机hosts中
FILE=$APP_PATH/hosts.base
if [ -f "$FILE" ]; then
   echo "$FILE exist"
   cat $FILE > /etc/hosts
else
   echo "$FILE not exist"
fi
# 获取到的Github相关hosts追加到其中
cat hosts >> /etc/hosts

git status
git add .
git commit -m "updated latest hosts"
git push

