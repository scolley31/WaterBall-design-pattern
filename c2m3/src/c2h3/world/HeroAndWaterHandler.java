package c2h3.world;

public class HeroAndWaterHandler extends CollisionHandler {

	HeroAndWaterHandler(CollisionHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public void doHandling(Sprite s1, Sprite s2, World world) {
		if (s1 instanceof Water && s2 instanceof Hero) {
			Hero hero = (Hero)s2;
			hero.increaseHp(10);
			world.removeSprite(s1);
		} else if (s1 instanceof Hero && s2 instanceof Water) {
			Hero hero = (Hero)s1;
			hero.increaseHp(10);
			world.removeSprite(s2);
		}
	}

	@Override
	public boolean match(String condition) {
		return "HW".equals(condition);
	}

}
