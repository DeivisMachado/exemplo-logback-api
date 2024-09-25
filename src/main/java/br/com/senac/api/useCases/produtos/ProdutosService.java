package br.com.senac.api.useCases.produtos;

import br.com.senac.api.entitys.Produtos;
import br.com.senac.api.useCases.produtos.domains.ProdutosResponseDom;
import br.com.senac.api.useCases.produtos.implement.ProdutosRepository;
import br.com.senac.api.utils.paginacao.PaginacaoRequest;
import br.com.senac.api.utils.paginacao.PaginacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ProdutosService {

    private static final Logger logger = LoggerFactory.getLogger(ProdutosService.class);

    public PaginacaoResponse<List<ProdutosResponseDom>>
        carregarProdutos(PaginacaoRequest request){
        PageRequest paginacao = PageRequest.of(
                request.getPagina() - 1 < 0 ? 0: request.getPagina(),
                request.getTamanho(),
                Sort.Direction.valueOf("ASC"),
                request.getOrderBy());
        Page<Produtos> produtosResult = produtosRepository.findAll(paginacao);

        if (!produtosResult.isEmpty()) {
            List<ProdutosResponseDom> produtosResponse = produtosResult.stream().map(row -> new ProdutosResponseDom(
                    row.getId(),
                    row.getNome(),
                    row.getDescricao())).toList();

            PaginacaoResponse <List<ProdutosResponseDom>> paginaResponse =
                    new PaginacaoResponse<>();

            paginaResponse.setPagina(produtosResult.getNumber());
            paginaResponse.setTamanho(produtosResult.getSize());
            paginaResponse.setTotal(produtosResult.getTotalPages());
            paginaResponse.setData(produtosResponse);

            return paginaResponse;
        }

        return null;

    }

    @Autowired
    private ProdutosRepository produtosRepository;

    public List<ProdutosResponseDom> carregarProdutos() {
        List<Produtos> produtosResult = produtosRepository.findAll();

        if (!produtosResult.isEmpty()) {
            List<ProdutosResponseDom> produtosResponse = produtosResult.stream().map(row -> new ProdutosResponseDom(
                    row.getId(), row.getNome(),
                    row.getDescricao())).toList();
        }

        return null;
    }

    @Autowired
    public void carregarEExibirProdutos() {
        List<ProdutosResponseDom> produtos = carregarProdutos();
        if (produtos != null && !produtos.isEmpty()) {
            logger.info("Produtos carregados:");
            for (ProdutosResponseDom produto : produtos) {
                logger.info("ID: {}, Nome: {}, Descrição: {}", 
                    produto.getId(), produto.getNome(), produto.getDescricao());
            }
        } else {
            logger.info("Nenhum produto encontrado.");
        }
    }
}
