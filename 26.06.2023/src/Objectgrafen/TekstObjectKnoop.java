package Objectgrafen;

import java.util.HashSet;

/**
 * @invar | getTekst() != null
 */
public class TekstObjectKnoop extends ObjectKnoop {
	
	/**
	 * @invar | tekst != null
	 */
	String tekst;

	/**
	 * @throws IllegalArgumentException | tekst == null
	 * @post | getVerwijzers().isEmpty()
	 * @post | getTekst() == tekst
	 */
	public TekstObjectKnoop(String tekst) {
		if (tekst == null)
			throw new IllegalArgumentException("Tekst kan niet null zijn");
		else
			this.tekst = tekst;
			this.verwijzers = new HashSet<LijstObjectKnoop>();
	}
	

	public String getTekst() {
		return this.tekst;
	}

	
	/**
	 * @throws IllegalArgumentException | builder == null
	 * @inspects | this
	 * @mutates | builder
	 * @post | builder.toString().startsWith(old(builder.toString()))
	 */
	@Override
	public void voegTekstueleVoorstellingToeAan(StringBuilder builder) {
		if (builder == null)
			throw new IllegalArgumentException("Builder kan niet null zijn");
		builder.append('"');
		builder.append(tekst);
		builder.append('"');
	}
	
	@Override
	public boolean heeftZelfdeInhoudAls(ObjectKnoop andere) {
		if (andere == null)
			throw new IllegalArgumentException("Input kan niet null zijn");
		return andere instanceof TekstObjectKnoop t && t.getTekst() == getTekst();
	}

}
