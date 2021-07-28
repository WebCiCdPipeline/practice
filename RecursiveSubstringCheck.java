package practice;

public class RecursiveSubstringCheck {


		public String removeSubstrings(String data,String remove) {
			if(data==null||remove==null) {
				return data;
			}
			
			char[] details = data.toCharArray();
			int[] indexes = new int[details.length];
			
			char[] removal = remove.toCharArray();
			
			
			int checkCount = ignoreAlphabeticalCharacters(details, indexes);
			int[] checkMap = new int[checkCount];
			String check = extractDataToClean(details, indexes, checkMap);
			int[] checkIndexes = new int[check.length()];
			setNegativeIndexes(checkIndexes);
			
			int subMapCount = firstPassRemoval(indexes, remove, removal, checkMap, check, checkIndexes);
			
			int[] subCheckMap = checkMap;
			int[] subCheckIndexes = checkIndexes;
			String subCheck = check;
			while(subMapCount!=subCheck.length()) {
				
				char[] subChecks = subCheck.toCharArray();
				subCheck = getLoopSubCheck(subCheckMap, subCheckIndexes, subCheck, subChecks);

				subCheckIndexes = new int[subCheck.length()];
				setNegativeIndexes(subCheckIndexes);
				subChecks = subCheck.toCharArray();
				findValuesToRemove(remove, removal, subCheckIndexes, subCheck, subChecks);
				subMapCount =0;
				subMapCount = markIndexesForRemoval(indexes, subCheckMap, subCheckIndexes, subMapCount);

			}

			return getOutputString(details, indexes);
			
		}

		private void setNegativeIndexes(int[] checkIndexes) {
			for(int i=0;i<checkIndexes.length;i++) {
				checkIndexes[i]=-1;
			}
		}

		private void findValuesToRemove(String remove, char[] removal, int[] subCheckIndexes, String subCheck,
				char[] subChecks) {
			for(int i=0;i<subCheck.length()&& i + remove.length()<=subCheck.length();i++) {
				char current = subChecks[i];
				if(current!=removal[0]) {
					continue;
				}
				String window = subCheck.substring(i,i+remove.length());
				if(!window.equals(remove)) {
					continue;
				}
				int checkIndex = subCheckIndexes[i];
				int j;
				if(checkIndex==-1) {
					j=i;
				}else {
					j = checkIndex + remove.length();
				}
				for(;j<i+remove.length();j++) {
					subCheckIndexes[j]=i;
				}
			}
		}

		private String getLoopSubCheck(int[] subCheckMap, int[] subCheckIndexes, String subCheck, char[] subChecks) {
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
			return subCheck;
		}

		private String getOutputString(char[] details, int[] indexes) {
			StringBuilder result = new StringBuilder();
			for(int i=0;i<details.length;i++) {
				char detail = details[i];
				if(indexes[i]<=1) {
					result.append(detail);
					
				}
			}
			return result.toString();
		}

		private int firstPassRemoval(int[] indexes, String remove, char[] removal, int[] checkMap, String check,
				int[] checkIndexes) {
			
			char[] checks = check.toCharArray();
			findValuesToRemove(remove, removal, checkIndexes, check, checks);
			int subMapCount =0;
			subMapCount = markIndexesForRemoval(indexes, checkMap, checkIndexes, subMapCount);
			return subMapCount;
		}

		private int markIndexesForRemoval(int[] indexes, int[] checkMap, int[] checkIndexes, int subMapCount) {
			for(int i=0;i<checkIndexes.length;i++) {
				int checkIndex = checkIndexes[i];
				if(checkIndex==-1) {
					subMapCount++;
					continue;
				}
				int mainIndex = checkMap[i];
				indexes[mainIndex]=2;
			}
			return subMapCount;
		}

		private String extractDataToClean(char[] details, int[] indexes, int[] checkMap) {
			StringBuilder checkBuilder = new StringBuilder();
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
			return check;
		}

		private int ignoreAlphabeticalCharacters(char[] details, int[] indexes) {
			int checkCount=details.length;
			for(int i=0;i<details.length;i++) {
				char detail =details[i];
				if(Character.isAlphabetic(detail)) {
					indexes[i]=1;
					checkCount--;
				}
			}
			return checkCount;
		}

	}



