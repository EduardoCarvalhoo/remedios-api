package com.remedios.connections;


import com.remedios.constantes.RabbitmqConstantes;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConection {
    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    //Amqm interface responsável por conectar o rabbitMQ e fazer a criação das filas
    public RabbitMQConection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    // vai retornar a fila
    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    //Configurando a exchange padrão
    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    //Relacionamento entre a fila e a exchange
    private Binding relacionamento(Queue fila, DirectExchange troca) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    // Método que utiliza as funções criadas e criar as filas dentro do rabbitMQ
    // Assim que a classe é construida ele vai executar o que tá dentro desse método
    @PostConstruct
    private void adiciona() {
        Queue filaRemedio = this.fila(RabbitmqConstantes.FILA_REMEDIO);
        Queue filaRemedioEspera = this.fila(RabbitmqConstantes.FILA_REMEDIO_ESPERA);

        // Vai receber a mensagem e direcionar para fila
        DirectExchange troca = this.trocaDireta();

        Binding ligacaoRemedio = this.relacionamento(filaRemedio, troca);
        Binding ligacaoRemedioEspera = this.relacionamento(filaRemedioEspera, troca);


        //Criando as filas no RabbitMQ
        this.amqpAdmin.declareQueue(filaRemedio);
        this.amqpAdmin.declareQueue(filaRemedioEspera);

        //Criando as exchanges
        this.amqpAdmin.declareExchange(troca); // Essa exchange já existe

        this.amqpAdmin.declareBinding(ligacaoRemedio);
        this.amqpAdmin.declareBinding(ligacaoRemedioEspera);
    }
}
