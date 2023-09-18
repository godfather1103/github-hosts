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

$JAVA_HOME/bin/java -jar target/github-hosts-1.0-SNAPSHOT.jar

# 记录任务完成的时间
echo '完成时间：' `date` >> ./runLog

git status
git add .
git commit -m "updated latest hosts"
git push
echo '!!! push success ！！！时间：' `date` >> ./runLog

