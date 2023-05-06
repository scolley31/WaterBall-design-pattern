package c2h3.world;

public class HeroAndFireHandler extends CollisionHandler {

	HeroAndFireHandler(CollisionHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public void doHandling(Sprite s1, Sprite s2, World world) {
		Hero hero = null;
		Fire fire = null;
		
		if (s1 instanceof Hero && s2 instanceof Fire) {
			hero = (Hero) s1;
	        fire = (Fire) s2;
		} else if (s1 instanceof Fire && s2 instanceof Hero) {
			hero = (Hero) s2;
	        fire = (Fire) s1;
		}
		
		hero.decreaseHp(10);
		if (hero.isDead()) {
			world.removeSprite(s2);
		}
		world.removeSprite(fire);
	}

	@Override
	public boolean match(String condition) {
		return "FH".equals(condition);
	}
}
