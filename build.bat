mvn clean package -Dmaven.test.skip=true && cd facade/target && jar cvf stocktrade.jar lib/* facade-1.0.jar && move stocktrade.jar ../..
pause