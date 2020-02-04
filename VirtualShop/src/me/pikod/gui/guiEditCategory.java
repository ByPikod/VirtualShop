package me.pikod.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.pikod.main.Color;
import me.pikod.main.VirtualShop;

public class guiEditCategory extends guiManager {
	public guiEditCategory(Player player, short categorySlot) {
		this.create(1, guiErisim.edit_category, "editCategory");
		
		@SuppressWarnings("deprecation")
		ItemStack item = new ItemStack(Material.getMaterial(VirtualShop.shops.getConfigurationSection("categories."+categorySlot).getInt("item")), 1);
		item.setDurability((short) VirtualShop.shops.getInt("categories."+categorySlot+".subID"));
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Color.chat(VirtualShop.shops.getString("categories."+categorySlot+".displayName")));
		
		List<String> lore = new ArrayList<String>();
		
		meta.setLore(lore);
		item.setItemMeta(meta);
		this.setItem(4, item);
		
		ItemStack kaldir = new ItemStack(Material.LAVA_BUCKET, 1);
		meta = kaldir.getItemMeta();
		meta.setDisplayName(Color.chat("&4Kategoriyi Sil"));
		lore.clear();
		lore.add(Color.chat("&cKategoriyi sonsuza"));
		lore.add(Color.chat("&ckadar kald�r�r!"));
		meta.setLore(lore);
		kaldir.setItemMeta(meta);
		
		this.setItem(8, kaldir);
		
		ItemStack slot = new ItemStack(Material.COMPASS, 1);
		meta = slot.getItemMeta();
		meta.setDisplayName(Color.chat("&aKategorinin Yeri (Slot)"));
		lore.clear();
		lore.add(""+categorySlot);
		meta.setLore(lore);
		slot.setItemMeta(meta);
		
		this.setItem(3, slot);
		
		ItemStack esyaSayisi = new ItemStack(Material.CHEST, 1);
		meta = esyaSayisi.getItemMeta();
		meta.setDisplayName(Color.chat("&aKategorideki E�ya Say�s�"));
		lore.clear();
		if(VirtualShop.shops.getConfigurationSection("categories."+categorySlot+".shop") != null) {
			lore.add(""+VirtualShop.shops.getConfigurationSection("categories."+categorySlot+".shop").getKeys(false).size());
		}else {
			lore.add("0");
		}
		
		meta.setLore(lore);
		esyaSayisi.setItemMeta(meta);
		
		this.setItem(5, esyaSayisi);
		ItemStack decoration = new ItemStack(Material.STAINED_GLASS_PANE);
		if(VirtualShop.shops.getBoolean("categories."+categorySlot+".decoration")) {
			decoration.setDurability((short) 5);
		}else {
			decoration.setDurability((short) 14);
		}
		
		meta = decoration.getItemMeta();
		meta.setDisplayName(Color.chat("&7Dekorasyon Modu"));
		lore.clear();
		if(VirtualShop.shops.getBoolean("categories."+categorySlot+".decoration")) {
			lore.add(Color.chat("&aA��k"));
		}else {
			lore.add(Color.chat("&cKapal�"));
		}
		
		meta.setLore(lore);
		decoration.setItemMeta(meta);	
		
		this.setItem(7, decoration);
		
		ItemStack geri = new ItemStack(Material.BARRIER);
		meta = geri.getItemMeta();
		meta.setDisplayName(Color.chat("&4Geri d�n"));
		lore.clear();
		lore.add(Color.chat("&cBir �nceki men�ye"));
		lore.add(Color.chat("&cd�nmenizi sa�lar!"));
		meta.setLore(lore);
		geri.setItemMeta(meta);	
		
		this.setItem(0, geri);
		
		player.openInventory(this.getInventory());
	}
}
