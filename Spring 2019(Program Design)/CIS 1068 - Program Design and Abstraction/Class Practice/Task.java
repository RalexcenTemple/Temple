
public class Task {
	private String name;
	private int priority;
	private int estMinsToComplete;
	
	
	public Task(String NAME, int PRIORITY, int EMTC) {
		name = NAME;
		priority = PRIORITY;
		estMinsToComplete = EMTC;
	}
	public Task() {
		name = null;
		priority = 0;
		estMinsToComplete = 0;
	}
	
	public void setName(String NAME) {
		this.name = NAME;
	}
	public void setPriority(int pri) {
		this.priority = pri;
	}
	public void setEMTC(int EMTC) {
		this.estMinsToComplete = EMTC;
	}
	
	public String getName() {
		return this.name;
	}
	public int getPriority() {
		return this.priority;
	}
	public int getEMTC() {
		return this.estMinsToComplete;
	}
	
	public String toString() {
		String out = ("Task: " + this.getName()+ " | Has Priority: " + this.getPriority() + " | with " + this.getEMTC() + " estimated minutes to completion");
		return out;
	}
	
	public void increasePriority(int amount) {
		if(amount > 0) {
			int current = this.getPriority();
			int updated = current + amount;
			this.setPriority(updated);
		}
	}
	
	public void decreasePriority(int amount) {
		if(amount > 0) {
			int current = this.getPriority();
			if(amount > current) {
				this.setPriority(0);
			}else {
				int updated = current - amount;
				this.setPriority(updated);
			}
		}
	}

}
