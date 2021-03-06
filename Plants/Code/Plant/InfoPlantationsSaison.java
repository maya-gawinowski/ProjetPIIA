/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

public class InfoPlantationsSaison {
	private String nomFichier = "plantationsSaison.txt";
	private String[] paragraphe = new String[4];
	
	public InfoPlantationsSaison() {}
	
	
	/**
	 * prépare les informations contenu dans le fichier
	 * en extrayant le texte du fichier et 
	 * en le séparant en 4 partie distinct qui correspondent
	 * aux saisons et les stocke dans l'attribut "paragraphe" 
	 * de notre classe 
	 */
	private void prepListEvent() {
		Lecture read = new Lecture(nomFichier);
		String fichierComplet = read.readFilePlantation(nomFichier);
		
		String[] split = fichierComplet.split("---", 4);
		
		for(int i = 0; i < split.length; i++) {
			if(split[i].contains("HIVER:")){
				String[] temp = split[i].split("HIVER:", 2);
				paragraphe[0] = temp[1];
			}else if(split[i].contains("PRINTEMPS:")) {
				String[] temp = split[i].split("PRINTEMPS:", 2);
				paragraphe[1] = temp[1];
			}else if(split[i].contains("ETE:")) {
				String[] temp = split[i].split("ETE:", 2);
				paragraphe[2] = temp[1];
			}else if(split[i].contains("AUTOMNE:")) {
				String[] temp = split[i].split("AUTOMNE:", 2);
				paragraphe[3] = temp[1];
			}
		}
	}
	
	/**
	 * renvoie les inforamtions liées à la saison
	 * @param s la saison
	 * @return les informations sur la saison
	 */
	public String infoSurSaison(Saison s) {
		prepListEvent();
		String infoPourSaison = "";
		if(s == Saison.HIVER) {
			infoPourSaison = paragraphe[0];
		}else if(s == Saison.PRINTEMPS) {
			infoPourSaison = paragraphe[1];
		}else if(s == Saison.ETE) {
			infoPourSaison = paragraphe[2];
		}else if(s == Saison.AUTOMNE) {
			infoPourSaison = paragraphe[3];
		}
		return infoPourSaison;
	}
	
	
}
