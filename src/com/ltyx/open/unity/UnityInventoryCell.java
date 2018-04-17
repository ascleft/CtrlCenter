package com.ltyx.open.unity;

public class UnityInventoryCell {

	public String UskinCode;
	public String LuthaiCode;
	public double All;
	public double Available;
	public double Locked;
	public String Department;
	public String Warehouse;

	public UnityInventoryCell(String uskinCode, String luthaiCode, double all, double available, double locked, String department, String warehouse) {
		init(uskinCode, luthaiCode, all, available, locked, department, warehouse);
	}

	public UnityInventoryCell(String uskinCode, String luthaiCode, double all, double locked, String department, String warehouse) {
		init(uskinCode, luthaiCode, all, locked, department, warehouse);
	}

	public void init(String uskinCode, String luthaiCode, double all, double available, double locked, String department, String warehouse) {

		UskinCode = uskinCode;
		LuthaiCode = luthaiCode;

		All = all;
		Available = available;
		Locked = locked;

		if (all == 0) {
			All = 0;
			Available = 0;
			Locked = 0;
		} else {
			if (all != available) {
				All = all;
				if (locked != 0) {
					Available = all - locked;
					Locked = locked;
				} else {
					Available = available;
					Locked = all - available;
				}
			} else {
				All = all;
				Available = available;
				Locked = locked;
			}
		}

		Department = department;
		Warehouse = warehouse;
	}

	public void init(String uskinCode, String luthaiCode, double all, double locked, String department, String warehouse) {
		double available = all - locked;
		init(uskinCode, luthaiCode, all, available, locked, department, warehouse);

	}

}
