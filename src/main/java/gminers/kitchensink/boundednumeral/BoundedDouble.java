package gminers.kitchensink.boundednumeral;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BoundedDouble
		extends BoundedNumber {
	double	value;
	
	public BoundedDouble(final double value, final double minimum, final double maximum) {
		super(minimum, maximum);
		this.value = value;
	}
	
	public void setValue(final double value) {
		boundsCheck(value);
		this.value = value;
	}
	
	@Override
	public int intValue() {
		return (int) value;
	}
	
	@Override
	public long longValue() {
		return (long) value;
	}
	
	@Override
	public float floatValue() {
		return (float) value;
	}
	
	@Override
	public double doubleValue() {
		return value;
	}
	
}
