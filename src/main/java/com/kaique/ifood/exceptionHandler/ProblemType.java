package com.kaique.ifood.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ASSOCOACAO_INVALIDA("/associacao-invalida" , "Associação invalida"),
	FORMA_PAGAMENTO_JA_EXISTENTE("/forma-pagamento-ja-existente", "Forma de pagamento já existente"),
	EMAIL_EM_USO("/email-em-uso","Email em uso"),
	DADO_INVALIDO("/dado-invalido" , "Dado inválido"),
	ERRO_DE_SISTEMA("/erro-interno" , "erro interno"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado" , "Recurso não encontrado"),
	PARAMETRO_INVALIDO("/parametro-invalido" , "Parâmetro Inválido" ),
	CORPO_ILEGIVEL("/corpo-ilegivel" ,"Corpo ilegível"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada" , "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso" , "Entidade em uso"),
	CHAVE_ESTRANGEIRA_NAO_ENCONTRA("/chave-estrangeira-nao-encontrada" , "Chave estrangeira não encontrada"),
	NEGOCIO( "/negocio", "Negócio"),
	SENHA_INVALIDA("/senha-invalida" , "Senha invalida");
	
	private String url;
	private String title;
	
	ProblemType(String path, String title) {
		this.url = "https//kaique.com.br" + path;
		this.title = title;
	}	
}
