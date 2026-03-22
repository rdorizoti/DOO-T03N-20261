package controller;

import Entities.Venda;
import io.VendaIO;
import service.VendaService;

import java.util.ArrayList;

public class VendaController {

    // onde serão armazenadas as vendas
    private  ArrayList<Venda> vendas = new ArrayList<>();
    private VendaIO io;
    private VendaService service;

    public VendaController(VendaIO io, VendaService service) {
        this.io = io;
        this.service = service;
    }

    // Fluxo do programa
    public void iniciar() {

        int op;

        do {
            op = io.mostraMenuInicio();
            processaOpInicio(op);
        } while (op != 4);
    }

    // direciona usuário para opção escolhida
    private void processaOpInicio(int op) {

        switch (op){

            case 1:
                cadastraVenda();
                break;

            case 2:
               // mostraOpListagem();
                break;

            case 3:
                mostraOpCalculo();
                break;

            case 4:
                io.exibeSaida();
                break;

            default:
                io.exibeOpInvalida();
                break;
        }
    }

    // menu de listagem
    private void mostraOpListagem(){
        int op;

        do {
            op = io.mostraMenuListagem();
            processaOpListagem(op);

        } while (op != 6);
    }

    // direciona usuário para opção escolhida
    private void processaOpListagem(int op) {

        switch (op){

            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

            default:
                io.exibeOpInvalida();
                break;
        }
    }

    // Menu de cálculo
    private void mostraOpCalculo(){
        int op;

        do {
            op = io.mostraMenuCalculo();
            processaOpCalculo(op);

        } while (op != 4);
    }

    // direciona usuário para opção escolhida
    private void processaOpCalculo(int op) {

        switch (op){

            case 1:
                calculaPreco();
                break;

            case 2:
                calculaTroco();
                break;

            case 3:
                calculaDesconto();
                break;

            case 4:
                break;

            default:
                io.exibeOpInvalida();
                break;
        }
    }

    // Cadastra nova compra
    private void cadastraVenda() {
        int quant = io.pedeQundidade();
        double valUni = io.pedeValor();

        double valVenda = service.calculaPreco(quant,valUni);
        double desconto = service.calculaDesconto(quant,valVenda);
        vendas.add(service.cadastraVenda(quant,valVenda,desconto));

        io.exibeSucessoCadastro();
    }

    // Lista compras realizadas
    private void listaCompras() {

        if (vendas.isEmpty()) {
            System.out.println("\nNenhuma compra cadastrada.");
            return;
        }

        System.out.printf(
                "\n--------------------------------------------------------------------------\n" +
                "  %-3s   %-10s   %-10s   %-10s   %-10s   %-10s  \n" +
                "--------------------------------------------------------------------------\n",
                "ID", "DATA", "QUANTIDADE", "V.TOTAL", "DESCONTO", "V.PAGO");

        for (Venda m : vendas) {
            System.out.println(m);
        }

        System.out.println("--------------------------------------------------------------------------");

    }

    // Mostra menu de Cálculo

    // Calculo do Preço
    private void calculaPreco() {

        int quant = io.pedeQundidade();
        double valUni = io.pedeValor();

        double preco = service.calculaPreco(quant,valUni);

        io.mostraResultadoVenda(preco);

    }

    // Calculo do troco
    private void calculaTroco() {

        double valPag = io.pedeValorPago();
        double valDev = io.pedeValorDevido();

        double troco = service.calculaTroco(valPag,valDev);

        io.mostraResultadoTroco(troco);

    }

    // calcula desconto
    private void calculaDesconto(){

        int quant = io.pedeQundidade();
        double valTot = io.pedeValorTotal();

        double desconto = service.calculaDesconto(quant, valTot);

        io.mostraResultadoDesconto(desconto);
    }

}
