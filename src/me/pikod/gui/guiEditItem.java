package me.pikod.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.pikod.main.Color;
import me.pikod.main.VirtualShop;

public class guiEditItem extends guiManager {
	@SuppressWarnings("deprecation")
	public guiEditItem(Player player, int item, int category) {
		this.create(1, guiErisim.edit_item, "editItem");
		
		ItemStack geri = new ItemStack(Material.BARRIER, 1);
		ItemMeta meta = geri.getItemMeta();
		meta.setDisplayName(Color.chat("&4Geri D�n"));
		List<String> lore = new ArrayList<String>();
		lore.add(Color.chat("&cBir �nceki men�ye"));
		lore.add(Color.chat("&cd�nmenizi sa�lar!"));
		meta.setLore(lore);
		geri.setItemMeta(meta);
		this.setItem(0, geri);
		
		
		ItemStack itemS = new ItemStack(Material.getMaterial(VirtualShop.shops.getConfigurationSection("categories."+category+".shop."+item).getInt("item")), VirtualShop.shops.getConfigurationSection("categories."+category+".shop."+item).getInt("count"));
		meta = itemS.getItemMeta();
		itemS.setDurability((short) VirtualShop.shops.getConfigurationSection("categories."+category+".shop."+item).getInt("subId"));
		meta.setDisplayName("");
		lore.clear();
		lore.add(Color.chat("&aID: &2"+item));
		lore.add(Color.chat("&aKategori ID: &2"+category));
		meta.setLore(lore);
		
		ConfigurationSection shop = VirtualShop.shops.getConfigurationSection("categories."+category+".shop."+item);
		
		if(shop.isSet("displayName")) {
			meta.setDisplayName(shop.getString("displayName"));
		}
		
		if(shop.isSet("lore")) {
			for(String key : shop.getConfigurationSection("lore").getKeys(false)) {
				lore.add(shop.getString("lore."+key));
			}
			lore.add(Color.chat("&r"));
			meta.setLore(lore);
		}
		
		itemS.setItemMeta(meta);
		
		if(shop.isSet("ench")) {
			for(String ench : shop.getConfigurationSection("ench").getKeys(false)) {
				Enchantment enchObj = Enchantment.getById(Integer.parseInt(ench));
				itemS.addUnsafeEnchantment(enchObj, shop.getInt("ench."+ench));
			}
		}
		
		this.setItem(4, itemS);
		
		ItemStack kaldir = new ItemStack(Material.LAVA_BUCKET);
		meta = kaldir.getItemMeta();
		meta.setDisplayName(Color.chat("&4Kald�r"));
		lore.clear();
		lore.add(Color.chat("&cBu e�yay�, kategorisinden"));
		lore.add(Color.chat("&ckald�rmay� sa�lar!"));
		meta.setLore(lore);
		kaldir.setItemMeta(meta);
		this.setItem(8, kaldir);
		
		ItemStack alis = new ItemStack(Material.GOLD_INGOT);
		meta = alis.getItemMeta();
		
		String buyCost = VirtualShop.shops.getConfigurationSection("categories."+category+".shop."+item).getString("buyCost");
		String sellCost = VirtualShop.shops.getConfigurationSection("categories."+category+".shop."+item).getString("sellCost");
		
		String strBuy = VirtualShop.numberToStr(Long.parseLong(buyCost));
		String strSell = VirtualShop.numberToStr(Long.parseLong(sellCost));
		
		if(Long.parseLong(buyCost) == 0) {
			meta.setDisplayName(Color.chat("&aAl�� Fiyat� Belirle &2(Kapal�)"));
		}else {
			meta.setDisplayName(Color.chat("&aAl�� Fiyat� Belirle &2("+strBuy+")"));
		}
		lore.clear();
		lore.add(Color.chat("&2Al�� fiyat�n�"));
		lore.add(Color.chat("&2belirlemenizi sa�lar!"));
		meta.setLore(lore);
		alis.setItemMeta(meta);
		this.setItem(3, alis);
		
		ItemStack satis = new ItemStack(Material.IRON_INGOT);
		meta = satis.getItemMeta();
		if(Long.parseLong(sellCost) == 0) {
			meta.setDisplayName(Color.chat("&aSat�� Fiyat� Belirle &2(Kapal�)"));
		}else {
			meta.setDisplayName(Color.chat("&aSat�� Fiyat� Belirle &2("+strSell+")"));
		}
		
		lore.clear();
		lore.add(Color.chat("&2Sat�� fiyat�n�"));
		lore.add(Color.chat("&2belirlemenizi sa�lar!"));
		meta.setLore(lore);
		satis.setItemMeta(meta);
		this.setItem(5, satis);
		
		player.openInventory(this.getInventory());
	}
}
