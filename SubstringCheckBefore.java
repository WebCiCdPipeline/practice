package practice;

public class SubstringCheck {


		public String removeSubstrings(String data,String remove) {
			if(data==null||remove==null) {
				return data;
			}
			
			char[] details = data.toCharArray();
			int[] indexes = new int[details.length];
			
			int checkCount=details.length;
			for(int i=0;i<details.length;i++) {
				char detail =details[i];
				if(detail=='A') {
					indexes[i]=1;
					checkCount--;
				}
			}
			StringBuilder checkBuilder = new StringBuilder();
			int[] checkMap = new int[checkCount];
			for(int i=0,j=0;i<details.length;i++) {
				int status = indexes[i];
				if(status==0) {
					char detail = details[i];
					checkBuilder.append(detail);
					checkMap[j]=i;
					j++;
				}
			}
			String check = checkBuilder.toString();
			char[] checks = check.toCharArray();
			char[] removal = remove.toCharArray();
			int removeLength = remove.length();
			int[] checkIndexes = new int[check.length()];
			for(int i=0;i<checkIndexes.length;i++) {
				checkIndexes[i]=-1;
			}
			
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
			int subMapCount =0;
			for(int i=0;i<checkIndexes.length;i++) {
				int checkIndex = checkIndexes[i];
				if(checkIndex==-1) {
					subMapCount++;
					continue;
				}
				int mainIndex = checkMap[i];
				indexes[mainIndex]=2;
			}
			int[] subCheckMap = checkMap;
			int[] subCheckIndexes = checkIndexes;
			String subCheck = check;
			char[] subChecks = checks;
			while(subMapCount!=subCheck.length()) {
				StringBuilder subCheckBuilder = new StringBuilder();
				for(int i=0,j=0;i<subCheck.length();i++) {
					int checkIndex = subCheckIndexes[i];
					if(checkIndex>=0) {
						continue;
					}
					
					int position = subCheckMap[i];
					subCheckMap[j]=position;
					j++;
					char val = subChecks[i];
					subCheckBuilder.append(val);

				}
				subCheck = subCheckBuilder.toString();

				subCheckIndexes = new int[subCheck.length()];
				for(int i=0;i<subCheckIndexes.length;i++) {
					subCheckIndexes[i]=-1;
				}
				subChecks = subCheck.toCharArray();
				for(int i=0;i<subCheck.length()&& i + removeLength<=subCheck.length();i++) {
					char current = subChecks[i];
					if(current!=removal[0]) {
						continue;
					}
					String window = subCheck.substring(i,i+removeLength);
					if(!window.equals(remove)) {
						continue;
					}
					int checkIndex = subCheckIndexes[i];
					int j;
					if(checkIndex==-1) {
						j=i;
					}else {
						j = checkIndex + removeLength;
					}
					for(;j<i+removeLength;j++) {
						subCheckIndexes[j]=i;
					}
				}
				subMapCount =0;
				for(int i=0;i<subCheckIndexes.length;i++) {
					int checkIndex = subCheckIndexes[i];
					if(checkIndex==-1) {
						subMapCount++;
						continue;
					}
					int mainIndex = subCheckMap[i];
					indexes[mainIndex]=2;
				}

			}

			StringBuilder result = new StringBuilder();
			for(int i=0;i<details.length;i++) {
				char detail = details[i];
				if(indexes[i]<=1) {
					result.append(detail);
					
				}
			}
			return result.toString();
			
		}

	}



