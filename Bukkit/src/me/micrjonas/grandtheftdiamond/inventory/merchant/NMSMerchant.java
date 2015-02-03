package me.micrjonas.grandtheftdiamond.inventory.merchant;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import me.micrjonas.grandtheftdiamond.inventory.merchant.ReflectionUtils.NMSMerchantRecipe;
import me.micrjonas.grandtheftdiamond.inventory.merchant.ReflectionUtils.NMSMerchantRecipeList;

import org.bukkit.entity.Player;

public class NMSMerchant implements InvocationHandler {
	
	private NMSMerchantRecipeList o = new NMSMerchantRecipeList();
	public Object proxy;
	
	private transient Object c;
	
	@Override
	public Object invoke(Object proxy, Method m, Object[] args) {
		
		try {
			
			if (m == null || m.getName() == null)
				return null;
			
			Class<?> entityHuman = ReflectionUtils.getClassByName(ReflectionUtils.getNMSPackageName() + ".EntityHuman");
			
			if (m.getName().equals("a_") && args.length == 1 && args[0] != null && args[0].getClass().isInstance(entityHuman))
				this.a_(args[0]);
			
			else if (m.getName().equals("b") || m.getName().equals("m_")) //m_ = 1.6.4, b = 1.7.4
				return this.b();
			
			else if (m.getName().equals("getOffers") && args.length == 1)
				return this.getOffers(args[0]);
			
			else if (m.getName().equals("a"))
				this.a(args[0]);
			
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void a_(Object player) {  //Class = EntityHuman
		
		this.c = player;
		
	}

	public Object b() { // Return Class = EntityHuman
		
		return this.c;
		
	}

	public Object getOffers(Object player) {  //Return Class = MerchantRecipeList, player Class = EntityHuman
		
		return this.o.getHandle();
		
	}

	public void a(Object recipe) {  //recipe Class = MerchantRecipe
		
		this.o.add(new NMSMerchantRecipe(recipe));
		
	}



	public Player getBukkitEntity() {
		
		try {
			
			Class<?> c = ReflectionUtils.getClassByName(ReflectionUtils.getNMSPackageName() + ".EntityHuman");
			Method m = c.getDeclaredMethod("getBukkitEntity");
			m.setAccessible(true);
			
			return (Player) m.invoke(this.c);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public void clearRecipes() {
		
		this.o.clear();
		
	}
	
	public void setRecipes(NMSMerchantRecipeList recipes) {
		
		this.o = recipes;
		
	}
	
	public void openTrading(Object player, String title) { //player Class = EntityPlayer
		
		this.c = player;
		
		try {
			
			Class<?> clazz = ReflectionUtils.getClassByName(ReflectionUtils.getNMSPackageName() + ".EntityPlayer");
			Method m = clazz.getDeclaredMethod("openOffer",
					ReflectionUtils.getClassByName(ReflectionUtils.getNMSPackageName() + ".IMerchant"),
					String.class);
			
			m.setAccessible(true);
			m.invoke(player, this.proxy, title);
			
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}