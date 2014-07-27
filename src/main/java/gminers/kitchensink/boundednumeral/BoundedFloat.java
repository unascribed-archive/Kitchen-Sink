package gminers.kitchensink.boundednumeral;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class BoundedFloat
		extends BoundedNumber {
	float	value;
	
	public BoundedFloat(final float value, final float minimum, final float maximum) {
		super(minimum, maximum);
		this.value = value;
	}
	
	public void setValue(final float value) {
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
		return value;
	}
	
	@Override
	public double doubleValue() {
		return value;
	}
	
}
