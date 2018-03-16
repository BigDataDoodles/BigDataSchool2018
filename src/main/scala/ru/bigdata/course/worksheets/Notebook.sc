import org.apache.spark

println("Интерпретируемый интерактивный блокнот")
val file = "/home/blackjack/dev/scala/BigDataSchool2018/resources/conspiracy.txt"
val res =  spark.SparkFiles.get(file)




