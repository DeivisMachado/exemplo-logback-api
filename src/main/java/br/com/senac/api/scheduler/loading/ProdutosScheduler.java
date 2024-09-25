package br.com.senac.api.scheduler.loading;

import br.com.senac.api.useCases.produtos.ProdutosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProdutosScheduler {

    private static final Logger logger = LoggerFactory.getLogger(ProdutosScheduler.class);

    @Autowired
    private ProdutosService produtosService;

    @Scheduled(fixedRate = 60000) // Executa a cada 1 minuto (60000 milissegundos)
    public void carregarProdutosTrigger() {
        logger.info("Iniciando carregamento de produtos...");
        produtosService.carregarEExibirProdutos();
        logger.info("Carregamento de produtos concluído.");
    }
}
