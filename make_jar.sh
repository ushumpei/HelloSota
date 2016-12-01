current_working_dir=`pwd`

# 対象ごとに変更(特にapplication_name, package_name)
application_name=HelloSota
package_name=jp.co.yamarobo
application_root=./ # スクリプトを外だしする場合は、絶対パスでリポジトリのディレクトリを記述してください
main_class=${package_name}.Main

cd ${application_root}

# ライブラリのjarのダウンロード、存在する場合はスキップ
jars=(SRClientHelper.jar core-2.2.jar javase-2.2.jar jna-4.1.0.jar opencv-310.jar sotalib.jar)
for jar in ${jars[@]}; do
  if [ ! -e lib/${jar} ]; then
    wget https://github.com/vstoneofficial/SotaSample/blob/master/lib/${jar}?raw=true -O lib/${jar}
  fi
done

mkdir ./META-INF
cat << MANIFEST > META-INF/MANIFEST.MF
Manifest-Version: 1.0
Class-Path: . /home/vstone/lib/core-2.2.jar /home/vstone/lib/javase-2.2.jar /home/vstone/lib/jna-4.1.0.jar /home/vstone/lib/opencv-310.jar /home/vstone/lib/sotalib.jar /home/vstone/lib/SRClientHelper.jar 
Created-By: 1.8.0_51 (Oracle Corporation)
Main-Class: ${main_class}

MANIFEST

mkdir ./bin
javac -verbose src/${package_name//.//}/*.java -classpath 'lib/*' -d bin
jar cfm ${application_name}.jar ./META-INF/MANIFEST.MF ./sound ./title.wav -C ./bin ${package_name//.//}

rm -rvf ./META-INF ./bin
mv ${application_name}.jar ${current_working_dir}
