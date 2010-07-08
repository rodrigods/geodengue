package org.geodengue.client.panels;

import org.geodengue.client.services.GeoDengueServicesAsync;
import org.geodengue.client.services.GeoDengueServices.Util;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.TextField;

public class PessoaContaminadaPanel extends Panel{
	
	private static final GeoDengueServicesAsync GEO_DENGUE = Util.getInstance();
	
	public PessoaContaminadaPanel(){
		this.setTitle("Cadastro de Pessoa Contaminada");
		init();
	}

	public void init(){
		VerticalPanel vp = new VerticalPanel();
		final TextField x = new TextField();
		x.setLabel("Cordenada X: ");
		vp.add(x);

		final TextField y = new TextField();
		y.setLabel("Cordenada Y: ");
		vp.add(y);
		
		Button b = new Button("Salvar");
		
		b.addListener(new ButtonListenerAdapter(){
			
			@Override
			public void onClick(Button button, EventObject e) {
				
				GEO_DENGUE.salvaPessoaContaminada(x.getValueAsString(),y.getValueAsString(), new AsyncCallback<String>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Nao foi possivel cadastrar a pessoa contaminada.");
						// TODO Auto-generated method stub
						
					}

					@Override public void onSuccess(String result){// TODO Auto-generated method stub
						Window.alert("Pessoa Contaminada cadastrada com sucesso.");
					}
				});
				// TODO Auto-generated method stub
				
			}
		});
		
		this.add(vp);

	}
	
}
