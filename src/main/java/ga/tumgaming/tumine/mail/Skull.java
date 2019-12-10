package ga.tumgaming.tumine.mail;

public enum Skull {
	Inbox("ZWIyODE1Yjk5YzEzYmZjNTViNGM1YzI5NTlkMTU3YTYyMzNhYjA2MTg2NDU5MjMzYmMxZTRkNGY3ODc5MmM2OSJ9fX0=","inbox"),
	LetterBox("NGZhODljZTg1OTMyYmVjMWExYzNmMzFjYjdjMDg1YTViZmIyYWM3ZTQwNDA5NDIwOGMzYWQxMjM4NzlkYTZkYSJ9fX0=","letterBox");
	private String idTag;
	private String prefix = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";
	private Skull(String texture, String id) {
		this.idTag = id;
	}
}
