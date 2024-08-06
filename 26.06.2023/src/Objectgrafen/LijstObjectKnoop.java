package Objectgrafen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * @invar | getElements() != null
 * @invar | getElements().isEmpty() || getElements().stream().allMatch(o -> o.getVerwijzers().contains(this))
 */
public class LijstObjectKnoop extends ObjectKnoop {
	
	/**
	 * @representationObject
	 * @peerObjects
	 * @invar | lijst != null
	 * @invar | lijst.isEmpty() || lijst.stream().allMatch(o -> o.verwijzers.contains(this))
	 */
	ArrayList<ObjectKnoop> lijst;
	
	/**
	 * @post | getElements().isEmpty()
	 * @post | getVerwijzers().isEmpty()
	 */
	public LijstObjectKnoop() {
		this.lijst = new ArrayList<ObjectKnoop>();
		this.verwijzers = new HashSet<LijstObjectKnoop>();
	}
	
	/**
	 * @creates | result
	 * @post | result != null
	 */
	public ArrayList<ObjectKnoop> getElements() {
		return new ArrayList<ObjectKnoop>(lijst);
	}
	
	/**
	 * @peerObject
	 * @pre | index == getElements().size()
	 * @mutates_properties | this.getElements(), knoop.getVerwijzers()
	 * @post | getElements().get(index) == knoop
	 * @post | knoop.getVerwijzers().contains(this)
	 */
	public void setAt(int index, ObjectKnoop knoop) {
		lijst.add(index, knoop);
		knoop.verwijzers.add(this);
	}
	
	/**
	 * @peerObject
	 * @pre | 0 <= index && index < getElements().size()
	 * @mutates_properties | this.getElements(), old(getElements().get(index)).getVerwijzers()
	 * @post | getElements().size() == old(getElements()).size() - 1
	 * @post | !old(getElements().get(index)).getVerwijzers().contains(this)
	 */
	public void removeAt(int index) {
		lijst.get(index).verwijzers.remove(this);
		lijst.remove(index);
	}
	
	/**
	 * @pre | builder != null
	 * @inspects | this
	 * @mutates | builder
	 * @post | builder.toString().startsWith(old(builder.toString()))
	 */
	@Override
	public void voegTekstueleVoorstellingToeAan(StringBuilder builder) {
		builder.append('[');
		for (ObjectKnoop knoop : lijst) {
			knoop.voegTekstueleVoorstellingToeAan(builder);
			builder.append(',');
			
		}
		builder.append(']');
	}

	@Override
	public boolean heeftZelfdeInhoudAls(ObjectKnoop andere) {
		return andere instanceof LijstObjectKnoop l && l.getElements().equals(this.getElements());
	}
	
	public Stream naarStream() {
		return lijst.stream().flatMap(e -> e instanceof TekstObjectKnoop t ? Stream.of(t.getTekst()) : null);
	}
	
}
