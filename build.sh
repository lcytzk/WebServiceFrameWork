mvn clean package -Dmaven.test.skip=true
cd facade/target && zip stocktrade.zip stocktrade.jar lib/* facade-1.0.jar