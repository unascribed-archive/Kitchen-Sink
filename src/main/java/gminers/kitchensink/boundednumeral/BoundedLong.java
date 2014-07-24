package gminers.kitchensink.boundednumeral;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class BoundedLong
		extends BoundedNumber {
	long	value;
	
	public BoundedLong(final long value, final long minimum, final long maximum) {
		super(minimum, maximum);
		this.value = value;
	}
	
	public void setValue(final long value) {
		boundsCheck(value);
		this.value = value;
	}
	
	@Override
	public int intValue() {
		return (int) value;
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
