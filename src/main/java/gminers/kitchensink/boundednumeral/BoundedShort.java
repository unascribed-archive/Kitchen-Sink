package gminers.kitchensink.boundednumeral;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class BoundedShort
		extends BoundedNumber {
	short	value;
	
	public BoundedShort(final short value, final short minimum, final short maximum) {
		super(minimum, maximum);
		this.value = value;
	}
	
	public void setValue(final short value) {
		boundsCheck(value);
		this.value = value;
	}
	
	@Override
	public short shortValue() {
		return value;
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
