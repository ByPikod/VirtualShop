package me.pikod.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.pikod.main.Color;
import me.pikod.main.VirtualShop;

public class guiCategoriesAdmin extends guiManager {
	public guiCategoriesAdmin(Player player) {
		this.create(VirtualShop.shops.getInt("categoryMenuSize"), Color.chat(guiErisim.categories_admin_menu), "categoriesAdmin");
		for(int i = 0; i < this.getInventory().getSize(); i++) {
			if(VirtualShop.shops.getConfigurationSection("categories."+i) != null) {
				if(VirtualShop.shops.getConfigurationSection("categories."+i).getBoolean("decoration")) {
					ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
					item.setDurability((short) 15);
					ItemMeta meta = item.getItemMeta();
					List<String> lore = new ArrayList<String>();
					lore.add(Color.chat("Kald�rmak i�in t�klay�n!"));
					meta.setLore(lore);
					meta.setDisplayName(Color.chat("&r"));
					item.setItemMeta(meta);
					this.getInventory().setItem(i, item);
				}else {
					@SuppressWarnings("deprecation")
					Material m = Material.getMaterial(VirtualShop.shops.getConfigurationSection("categories."+i).getInt("item"));
					ItemStack item = new ItemStack(m, 1);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(Color.chat(VirtualShop.shops.getConfigurationSection("categories."+i).getString("displayName")));
					List<String> lore = new ArrayList<String>();
					lore.add(Color.chat("&2Sa� t�k:&a Kategori kald�rma/d�zenleme"));
					lore.add(Color.chat("&2Sol t�k:&a Kategori i�eri�i d�zenleme"));
					meta.setLore(lore);
					item.setItemMeta(meta);
					item.setDurability((short) VirtualShop.shops.getConfigurationSection("categories."+i).getInt("subID"));
					this.getInventory().setItem(i, item);
				}
			}
		}
		
		player.openInventory(this.getInventory());
	}
}
