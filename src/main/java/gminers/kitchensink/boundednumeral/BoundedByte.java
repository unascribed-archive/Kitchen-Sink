package gminers.kitchensink.boundednumeral;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BoundedByte
		extends BoundedNumber {
	private byte	value;
	
	public BoundedByte(final byte value, final byte minimum, final byte maximum) {
		super(minimum, maximum);
		this.value = value;
	}
	
	public void setValue(final byte value) {
		boundsCheck(value);
		this.value = value;
	}
	
	@Override
	public byte byteValue() {
		return value;
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
