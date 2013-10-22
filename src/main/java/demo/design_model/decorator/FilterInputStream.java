package demo.design_model.decorator;

public abstract class FilterInputStream implements InputStream{
	
	private InputStream in;
	
	public FilterInputStream(InputStream in){
		this.in = in;
	}
	
	@Override
	public void say() {
		in.say();
	}
}
