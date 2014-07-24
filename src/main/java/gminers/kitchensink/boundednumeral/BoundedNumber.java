package gminers.kitchensink.boundednumeral;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class BoundedNumber
		extends Number {
	double	minimum	= Double.POSITIVE_INFINITY;
	double	maximum	= Double.NEGATIVE_INFINITY;
	
	protected void boundsCheck(final double proposedValue) {
		if (proposedValue < minimum) {
			throw new IndexOutOfBoundsException(proposedValue + " < " + minimum);
		} else if (proposedValue > maximum) {
			throw new IndexOutOfBoundsException(proposedValue + " > " + minimum);
		}
	}
	
	public void setMinimum(final double minimum) {
		if (minimum > maximum) {
			throw new IllegalArgumentException("minimum cannot be greater than maximum");
		}
		this.minimum = minimum;
	}
	
	public void setMaximum(final double maximum) {
		if (maximum < minimum) {
			throw new IllegalArgumentException("maximum cannot be less than minimum");
		}
		this.maximum = maximum;
	}
}
