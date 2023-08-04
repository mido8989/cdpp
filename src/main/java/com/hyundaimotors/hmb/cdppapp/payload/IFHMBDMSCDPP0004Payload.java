package com.hyundaimotors.hmb.cdppapp.payload;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class IFHMBDMSCDPP0004Payload {
    
    @Schema(name = "IFHMBDMSCDPP0004Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "MM/DD/YYYY", example = "2019-09-10")
        private String date;
        @Schema(description = "It should be a pre-existing Dealer Code in CRM.", example = "proc00035047103")
        private String dealerCode;
        @Schema(description = "It should be an existing Api Key in CRM.", example = "4455")
        private String apiKey;
        @Schema(description = "Open or Done", example = "Open")
        private String statusLead;
    }

    @Schema(name = "IFHMBDMSCDPP0004Payload.Response")
    @Getter
    @Setter
    @SuperBuilder
    public static class Response{
        private Contact contact;
        private List<Veiculo> veiculo;
        private Opv opv;
        private FinanciamentoOPV financiamentoOPV;
        private List<Temperature> temperature;
        private List<Interaction> interaction;
        private Result result;
        
    }

    @Schema(name = "IFHMBDMSCDPP0004Payload.Contact")
    @Getter
    @Setter
    public static class Contact{
        @Schema(description = "Connex error code. Code 0 means success.", example = "0")
        private String errorspcCode;
        @Schema(description = "Connex error message.", example = "OK")
        private String errorspcMessage;
        @Schema(description = "Contact Nome", example = "Carlos")
        private String nome;
        @Schema(description = "Contact Sobrenome", example = "Cortez")
        private String sobrenome;
        @Schema(description = "Contact Gender", example = "Male")
        private String genero;
        @Schema(description = "MM/DD/YYYY", example = "21/10/1988")
        private String dataNascimento;
        @Schema(description = "Contact Telefone", example = "11976588860")
        private String telefone;
        @Schema(description = "Contact Email", example = "prueba@gmail.com")
        private String email;
        @Schema(description = "Contact Cpf", example = "71238856724")
        private String cpf;
        @Schema(description = "Contact Cep", example = "4007001")
        private String cep;
        @Schema(description = "Contact Endereco", example = "Rua Tutoia 879")
        private String endereco;
        @Schema(description = "Contact IDConnex", example = "1-6789YU")
        private String idconnex;
        @Schema(description = "mm/dd/yyyy hh24:mi:ss", example = "12/11/2017:12:00:00")
        private String dataPrevistaCarroNovo;
    }

    @Schema(name = "IFHMBDMSCDPP0004Payload.veiculo")
    @Getter
    @Setter
    public static class Veiculo{
        @Schema(description = "Veiculo Modelo", example = "HB20")
        private String Modelo;
        @Schema(description = "Veiculo Motor", example = "Manual 6")
        private String Motor;
        @Schema(description = "Veiculo Transmissao", example = "A")
        private String Transmissao;
        @Schema(description = "Veiculo Versao", example = "Comfort Plus")
        private String Versao;
        @Schema(description = "Veiculo Cor", example = "Blue Sky")
        private String Cor;
        @Schema(description = "Veiculo Preco", example = "50000")
        private String Preco;
    }

    @Schema(name = "IFHMBDMSCDPP0004Payload.opv")
    @Getter
    @Setter
    public static class Opv{
        @Schema(description = "Protocolo", example = "HMB8761468242")
        private String protocolo;
        @Schema(description = "CodigoConcessionaria", example = "B05AM19029")
        private String codigoConcessionaria;
        @Schema(description = "Origem", example = "Homepage")
        private String origem;
        @Schema(description = "Metodo", example = "Internet")
        private String metodo;
        @Schema(description = "mm/dd/yyyy hh24:mi:ss", example = "2020-03-04  4:42:45 PM")
        private String dataAbertura;
        @Schema(description = "NomeResponsavel", example = "HMBPrueba")
        private String nomeResponsavel;
        @Schema(description = "Status", example = "Open")
        private String status;
        @Schema(description = "Prioridade", example = "Normal")
        private String prioridade;
        @Schema(description = "Y or N", example = "Y")
        private String identificacaoPCD;
        @Schema(description = "TipoContato", example = "Customer")
        private String tipoContato;
        @Schema(description = "ClienteHyundai", example = "Y")
        private String clienteHyundai;
        @Schema(description = "UsadoNaTroca", example = "Y")
        private String usadoNaTroca;
        @Schema(description = "OptInCotacao", example = "N")
        private String optInCotacao;
        @Schema(description = "OptInContato", example = "N")
        private String optInContato;
        @Schema(description = "NomeConcessionaria", example = "CAOA - Barra II")
        private String nomeConcessionaria;
        @Schema(description = "StatusConcessionaria", example = "Active")
        private String statusConcessionaria;
        @Schema(description = "Comentario", example = "gostaria de simula 00E7 00F5es para financiamento do veiculo. em 60 vezes")
        private String comentario;
        @Schema(description = "MetodoContato", example = "Internet")
        private String metodoContato;
        @Schema(description = "ObservacoesContato", example = "gostaria de simula 00E7 00F5es para financiamento do veiculo. em 60 vezes")
        private String observacoesContato;
        @Schema(description = "DadosCampanha", example = "Massive Campaigns")
        private String dadosCampanha;
    }

    @Schema(name = "IFHMBDMSCDPP0004Payload.FinanciamentoOPV")
    @Getter
    @Setter
    public static class FinanciamentoOPV{
        @Schema(description = "Quer Finaciamento", example = "Y")
        private String querFinanciar;
        @Schema(description = "Tipo do Financiamento", example = "Programa de financiamento da Hyundai")
        private String tipoFinanciamento;
        @Schema(description = "Valor Incial Fianciamento", example = "50000")
        private String valorEntradaFinanciamento;
        @Schema(description = "Numero do parcelas", example = "12")
        private String qtdParcelaFinanciamento;
    }

    @Schema(name = "IFHMBDMSCDPP0004Payload.temperature")
    @Getter
    @Setter
    public static class Temperature{
        @Schema(description = "Numero Interaction", example = "2")
        private String temperatureNum;
        @Schema(description = "Description Interaction", example = "04/10/2018 - O cliente fez o testdrive")
        private String temperature;
    }
    @Schema(name = "IFHMBDMSCDPP0004Payload.temperature")
    @Getter
    @Setter
    public static class Interaction{
        private String interactionNum;
        private String description;
    }

    @Schema(name = "IFHMBDMSCDPP0004Payload.result")
    @Getter
    @Setter
    public static class Result{
        @Schema(description = "mm/dd/yyyy hh24:mi:ss", example = "2018-01-10  8:00:00 AM")
        private String firstInteractionDate;
        @Schema(description = "Sattaus Sales", example = "Sold")
        private String saleStatus;
        @Schema(description = "Reason Interaction", example = "Gave up the purchase right now")
        private String reason;
        @Schema(description = "Result Interaction", example = "Aten 00E7 00E3o!! Sua concession 00E1ria acaba de receber uma Oportunidade de Vendas")
        private String result;
        @Schema(description = "Sales CPF", example = "83336664853")
        private String saleCPF;
        @Schema(description = "mm/dd/yyyy hh24:mi:ss", example = "17/10/2018 16:30:00")
        private String endDate;
        @Schema(description = "mm/dd/yyyy hh24:mi:ss", example = "15/10/2018 16:30:00")
        private String dlrReceiveDate;
        @Schema(description = "Dealer Responde", example = "16/10/2018 16:30:00")
        private String dlrResponseTime;
    }
}
