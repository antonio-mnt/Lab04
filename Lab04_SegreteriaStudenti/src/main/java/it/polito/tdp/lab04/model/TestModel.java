package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */
		
		for(Corso c: model.getTuttiICorsi()) {
			System.out.println(c);
		}
		
		System.out.println(model.getStudente(146101));
		
		if(model.verificaIscrizione(146101, "02CIXPG") == true) {
			System.out.println("trueeeeeeeeeeeeeeee");
		}

	}

}
