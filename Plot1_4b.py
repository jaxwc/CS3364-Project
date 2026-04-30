import matplotlib.pyplot as plt
import pandas as pd

df = pd.read_csv("benchmark_compare_sorted.csv")


plt.figure(figsize=(8, 5))
for algo, group in df.groupby("algorithm"):
    plt.plot(group["n"], group["avg_time_ms"], marker="o", label=algo)

plt.xlabel("n (array size)")
plt.ylabel("Average runtime (ms)")
plt.title("Algorithm comparison (sorted input, 20 trials)")
plt.legend()
plt.grid(True, alpha=0.3, which="both")
plt.yscale("log")
plt.savefig("deliverable1_4b.png", dpi=150, bbox_inches="tight")
plt.show()
