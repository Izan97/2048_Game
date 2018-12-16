package Body;


public enum Direction {
	//UP, DOWN, RIGHT, LEFT;
	UP("UP"),DOWN("DOWN"),RIGHT("RIGHT"),LEFT("LEFT");
	
	private String parameterName;
	
	
	private Direction( String param){
		
		
		this.parameterName = param;
	}

	public static Direction parse(String param) { 
		for (Direction direction : Direction.values()) { 
			if (direction.parameterName.equalsIgnoreCase(param)) 
				return direction; 
		} 
		return null; 
	} 
	
	String externalise() { 
		return parameterName; 
	} 
	
	
}


