package demo.design_model.decorator;

public class StringBufferInputStream implements InputStream{
	@Override
	public void say() {
		System.out.print("StringBufferInputStream");
		
	}
}
