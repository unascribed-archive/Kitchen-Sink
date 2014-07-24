package gminers.kitchensink.boundednumeral;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BoundedInteger
		extends BoundedNumber {
	int	value;
	
	public BoundedInteger(final int value, final int minimum, final int maximum) {
		super(minimum, maximum);
		this.value = value;
	}
	
	public void setValue(final int value) {
		boundsCheck(value);
		this.value = value;
	}
	
	@Override
	public int intValue() {
		return value;
	}
	
	@Override
	public long longValue() {
		return value;
	}
	
	@Override
	public float floatValue() {
		return value;
	}
	
	@Override
	public double doubleValue() {
		return value;
	}
	
}
