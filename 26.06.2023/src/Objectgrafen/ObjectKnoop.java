package Objectgrafen;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @invar | getVerwijzers() != null
 */
public abstract class ObjectKnoop {
	
	// @invar | verwijzers != null
	/**
	 * @representationObject
	 * @peerObjects
	 */
	Set<LijstObjectKnoop> verwijzers;
	
	/**
	 * @creates | this
	 * @post | result != null
	 */
	public Set<LijstObjectKnoop> getVerwijzers() {
		return Set.copyOf(verwijzers);
	}
	
	/**
	 * @pre | builder != null
	 * @inspects | this
	 * @mutates | builder
	 * @post | builder.toString().startsWith(old(builder.toString()))
	 */
	public abstract void voegTekstueleVoorstellingToeAan(StringBuilder builder);
	
	public abstract boolean heeftZelfdeInhoudAls(ObjectKnoop andere);
	
	
	public Iterator<ObjectKnoop> linksAfstammelingenIterator() {
		return new Iterator<ObjectKnoop>() {
			ObjectKnoop k = ObjectKnoop.this;
			@Override
			public boolean hasNext() {
				return (k instanceof LijstObjectKnoop l && l.getElements().size() != 0);
			}
			
			@Override
			public ObjectKnoop next() {
				return ((LijstObjectKnoop)k).getElements().get(0);	
			}
		};
	}
	
	public void linksAfstammelingen(Consumer<? super ObjectKnoop> consumer) {
		Iterator<ObjectKnoop> i = linksAfstammelingenIterator();
		while (i.hasNext()) {
			ObjectKnoop k = i.next();
			if (k instanceof LijstObjectKnoop l && l.getElements().size() >= 3)
				consumer.accept(l);
		}
	}

}
