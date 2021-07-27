package practice;

public class SubstringCheck {

	public String removeSubstrings(String data,String remove) {
		if(data==null||remove==null) {
			return data;
		}
		
		char[] details = data.toCharArray();
		int[] indexes = new int[details.length];
		
		ignoreLetters(details, indexes);
		String check = buildCheckString(details, indexes);
		
		int[] checkIndexes = createCheckIndexes(check);
		
		populateCheckIndexes(remove, check, checkIndexes);
		
		return getString(details, indexes, checkIndexes);
		
	}

	private void populateCheckIndexes(String remove, String check, int[] checkIndexes) {
		char[] checks = check.toCharArray();
		char[] removal = remove.toCharArray();
		int removeLength = remove.length();
		for(int i=0;i<check.length()&& i + removeLength<=check.length();i++) {
			char current = checks[i];
			if(current!=removal[0]) {
				continue;
			}
			String window = check.substring(i,i+removeLength);
			if(!window.equals(remove)) {
				continue;
			}
			int checkIndex = checkIndexes[i];
			int j;
			if(checkIndex==-1) {
				j=i;
			}else {
				j = checkIndex + removeLength;
			}
			for(;j<i+removeLength;j++) {
				checkIndexes[j]=i;
			}
		}
	}

	private int[] createCheckIndexes(String check) {
		int[] checkIndexes = new int[check.length()];
		for(int i=0;i<checkIndexes.length;i++) {
			checkIndexes[i]=-1;
		}
		return checkIndexes;
	}

	private String buildCheckString(char[] details, int[] indexes) {
		StringBuilder checkBuilder = new StringBuilder();
		for(int i=0;i<details.length;i++) {
			int status = indexes[i];
			if(status==0) {
				char detail = details[i];
				checkBuilder.append(detail);
			}
		}
		String check = checkBuilder.toString();
		return check;
	}

	private void ignoreLetters(char[] details, int[] indexes) {
		for(int i=0;i<details.length;i++) {
			char detail =details[i];
			if(Character.isAlphabetic(detail)) {
				indexes[i]=1;
			}
		}
	}

	private String getString(char[] details, int[] indexes, int[] checkIndexes) {
		StringBuilder result = new StringBuilder();
		int checkIndex=0;
		for(int i=0;i<details.length;i++) {
			char detail = details[i];
			if(indexes[i]==1) {
				result.append(detail);
				continue;
			}
			int foundIndex = checkIndexes[checkIndex];
			checkIndex++;
			if(foundIndex<0) {
				result.append(detail);
			}
		}
		return result.toString();
	}

}
