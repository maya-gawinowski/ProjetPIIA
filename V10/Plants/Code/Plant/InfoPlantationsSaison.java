package Plant;

public class InfoPlantationsSaison {
	private String nomFichier = "plantationsSaison.txt";
	private String[] paragraphe = new String[4];
	
	public InfoPlantationsSaison() {}
	
	private void prepListEvent() {
		Lecture read = new Lecture(nomFichier);
		String fichierComplet = read.readFilePlantation(nomFichier);
		
		paragraphe = fichierComplet.split("---", 4);
	}
	
	public String infoSurSaison(Saison s) {
		prepListEvent();
		String infoPourSaison = "";
		switch (s) {
		case HIVER:
			infoPourSaison = paragraphe[0];
		case PRINTEMPS:
			infoPourSaison = paragraphe[1];
		case ETE:
			infoPourSaison = paragraphe[2];
		case AUTOMNE:
			infoPourSaison = paragraphe[3];
		default:
			break;
		}
		return infoPourSaison;
	}
	
	
}
