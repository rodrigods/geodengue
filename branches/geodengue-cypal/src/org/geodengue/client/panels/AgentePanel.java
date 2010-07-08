package org.geodengue.client.panels;

import org.geodengue.client.services.GeoDengueServicesAsync;
import org.geodengue.client.services.GeoDengueServices.Util;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.TextField;

public class AgentePanel extends Panel{

	private static final GeoDengueServicesAsync GEO_DENGUE = Util.getInstance();
	public AgentePanel() {
		this.setTitle("Cadastro de Agente");
		this.setSize(400, 400);
		init();
	}
	private void init() {
		VerticalPanel vp = new VerticalPanel();
		final TextField nome = new TextField();
		nome.setLabel("Nome: ");
		vp.add(nome);

		final TextField matricula = new TextField();
		matricula.setLabel("Matricula: ");
		vp.add(matricula);
		
		final TextField area = new TextField();
		area.setLabel("Area de Cobertura: ");
		vp.add(area);
		
		Button b = new Button("Salvar");
		
		b.addListener(new ButtonListenerAdapter(){
			
			@Override
			public void onClick(Button button, EventObject e) {
				
				GEO_DENGUE.salvaAgente(nome.getValueAsString(),matricula.getValueAsString(),area.getValueAsString(), new AsyncCallback<String>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Nao foi possivel cadastrar o agente.");
						// TODO Auto-generated method stub
						
					}

					@Override public void onSuccess(String result){// TODO Auto-generated method stub
						Window.alert("Agente cadastrado com sucesso.");
					}
				});
				// TODO Auto-generated method stub
				
			}
		});
		
		this.add(vp);
	}
}
