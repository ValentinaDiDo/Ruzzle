package it.polito.tdp.ruzzle.model;
import java.util.*;
public class Ricerca {

	public List<Pos> trovaParola(String parola, Board board){
		
		for(Pos p : board.getPositions()) {
			//devo capire se la lettera in post è == alla prima lettera della parola
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {  //restituisce la value property di p
				//faccio partire ricorsione
				List<Pos> parziale = new ArrayList<Pos>();
				parziale.add(p);
				if(cerca(parola,parziale,1,board))
					return parziale; //sarà riempito dal metodo cerca
			}
		}
		return null;
	}

	private boolean cerca(String parola, List<Pos> percorso, int livello, Board board) {
	
		if(livello == parola.length())
			return true;
		else {
			//recupero l'ultima posizione inserita 
			Pos ultima = percorso.get(percorso.size()-1);
			List<Pos> adiacenti = board.getAdjacencies(ultima);
			for(Pos a : adiacenti) {
				 if(!percorso.contains(a) && board.getCellValueProperty(a).get().charAt(0) == parola.charAt(livello)) {
					 //continuo percorso andando avanti con ricorsione
					 percorso.add(a);
					 cerca(parola, percorso, livello+1, board);
					 //non so se il percorso è valido, quindi faccio backtracking
					 percorso.remove(percorso.size()-1); //rimuovo con accesso posizionale perché ho una lista e non un set
				 }
			}
			
			
			
		}
		return true; //se è true la parola è corretta
	}
}
