package lab4;

import java.util.LinkedList;

public class StockMarket {
	
	LinkedList<Share> market;
	
	//CONSTRUCTOR
	public StockMarket(){
		market = new LinkedList<Share>();
	}
	
	//PUTINMARKET WHOLE SHARE
	public void PutinMarket(Share share){
		
		market.add(share);
		
	}
	
	//PUTINMARKET PART OF SHARE
	public void PutinMarket(StockOwner so, Corporation c, int qtd){
		
		Share share = null;
		
		for(int i = 0; i < so.portfolio.size(); i++){
			//SE A SHARE PERTENCER À EMPRESA C ESSA É A SHARE A METER NO MERCADO
			if(so.portfolio.get(i).c.equals(c)){
				//SE TIVER SHARES SUFICIENTES
				if(so.portfolio.get(i).quantity >= qtd)
					share = so.portfolio.get(i);
					share.quantity = qtd;
					break;
			}
		
		}
		
		market.add(share);
	
	}
	
	//BUY
	public void buy(StockOwner so, Corporation c, int qtd){
		
		Share share = new Share(qtd, so, c);
		StockOwner vendor;
		int lacking = qtd;
		
		//COMPRADOR
		so.debit(qtd * c.getValue());
		if(so.money < 0){
			System.out.println("No money");
			so.credit(qtd * c.getValue());
			return;
		}
		
		//VENDEDOR E MARKET
		for(int i = 0; i < market.size(); i++){
			//SE A SHARE PERTENCER À EMPRESA C ENTÃO ESSE SERÁ O VENDEDOR
			if(market.get(i).c.equals(c)){
				//SE TIVER SHARES SUFICIENTES RETIRAM SE E ACABA-SE O CICLO
				vendor = market.get(i).so;
				
				if(market.get(i).quantity > lacking){
					for(int j = 0; j < vendor.portfolio.size(); j++){
						if(vendor.portfolio.get(j).c.equals(c)){
													
							vendor.portfolio.get(j).quantity = vendor.portfolio.get(j).quantity - lacking;
							vendor.credit(lacking * c.getValue());
							market.get(i).quantity = market.get(i).quantity - lacking;
							return;
						}
					}
				//SE TIVER EXACTAMENTE AS SHARES A SEREM COMPRADAS
				}else if(market.get(i).quantity == lacking){
					for(int j = 0; j < vendor.portfolio.size(); j++){
						if(vendor.portfolio.get(j).c.equals(c)){
							vendor.remove(share);
							vendor.credit(lacking * c.getValue());
							market.remove(i);
							return;
						}
					}
				//SE TIVER MENOS SHARES DO QUE AS PRETENDIDAS
				}else if(market.get(i).quantity < lacking){
					for(int j = 0; j < vendor.portfolio.size(); j++){
						if(vendor.portfolio.get(j).c.equals(c)){
							vendor.remove(share);
							vendor.credit(vendor.portfolio.get(j).quantity * c.getValue());
							market.remove(i);
							//ACTUALIZA AS ACÇOES QUE FALTAM
							lacking = lacking - vendor.portfolio.get(j).quantity;
						}
					}
				}
				
			}
			//END IF COMPANHIA DO MARKET IGUAL
		}
		//END FOR
		
		
		so.portfolio.add(share);
		market.add(share);
		System.out.println("Transacção concluida");
		
	}
	
}
