# Ecommerce
![Diagrama C4](https://github.com/PedroBSantos/apache-kafka/blob/master/images/CMS.png)

## Comandos Kafka

- kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic name_topic
- kafka-topics --delete --bootstrap-server localhost:9092 --topic name_topic
- kafka-topics --list --bootstrap-server localhost:9092
- kafka-console-consumer --bootstrap-server localhost:9092 --topic topic_name --from-beginning
- kafka-topics --alter --bootstrap-server localhost:9092 --topic topic_name --partitions partitions_number

## Conceitos

Um tópico é composto por uma ou várias partições, onde cada partição é um array que armazena mensagens.
</br>
Toda vez que uma mensagem é publicada em um tópico X e na partição Y, o kafka atribui um offset a essa mensagem
dentro da partição que foi publicada. Esse offset é um valor numérico incremental que inicia em 0. O offset nada
mas é do que o index da mensagem dentro da partição. </br>
Uma partição não pode ser consumida por vários consumidores do mesmo grupo mas, um consumidor pode consumir várias partições. </br>
Ao criar um consumer é necessário definir a qual grupo este consumer esta associado. Um grupo
é uma forma de segregar a responsabilidade de consumo, ou seja, definir que tipo de ação os consumidores
daquele grupo irão realizar ao consumir a mensagem do tópico. Geralmente um consumidor consome apenas um tópico. </br>
O hash da chave da mensagem a ser publicada é utlizado para definir em qual partição do tópico a mesma será colocada. </br>
Para realizar paralelização de consumo de mensagens para um mesmo tópico é necessário que o número de partições seja maior ou igual ao número de consumidores de um grupo.</br>
A medida que os consumidores vão consumido mensagens das partições, o kafka pode realizar o processo de rebalanceamento que em alguns casos pode ocasionar em conflitos
de commit. Para amenizar esses conflitos é possível alterar o valor da propriedade max.poll.records. Essa propriedade refere-se a quantidade de records que devem
ser processados para que o consumer solicite ao kafka o commit de processamento.
